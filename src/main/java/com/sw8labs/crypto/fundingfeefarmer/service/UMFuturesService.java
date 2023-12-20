package com.sw8labs.crypto.fundingfeefarmer.service;

import com.binance.connector.futures.client.impl.UMFuturesClientImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sw8labs.crypto.fundingfeefarmer.dto.AccountInfoDTO;
import com.sw8labs.crypto.fundingfeefarmer.dto.MarkPriceDTO;
import com.sw8labs.crypto.fundingfeefarmer.repository.AccountInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class UMFuturesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UMFuturesService.class);

    private final UMFuturesClientImpl umFuturesClient;
    private final ObjectMapper objectMapper;
    private final AccountInfoRepository accountInfoRepository;

    @Autowired
    public UMFuturesService(UMFuturesClientImpl umFuturesClient, ObjectMapper objectMapper, AccountInfoRepository accountInfoRepository) {
        this.umFuturesClient = umFuturesClient;
        this.objectMapper = objectMapper;
        this.accountInfoRepository = accountInfoRepository;
    }

    public List<MarkPriceDTO> getTop5MarkPricesByAbsLastFundingRate() {
        String response = umFuturesClient.market().markPrice(new LinkedHashMap<>());
        ArrayList<MarkPriceDTO> markPrices = null;
        try {
            markPrices = objectMapper.readValue(response, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            LOGGER.error("Invalid JSON", e);
        }

        if (markPrices != null) {
            return getTop5(markPrices);
        }
        return markPrices;
    }

    private List<MarkPriceDTO> getTop5(List<MarkPriceDTO> markPrices) {
        List<MarkPriceDTO> top5FundingRates = markPrices.stream().filter(mp -> Math.abs(mp.lastFundingRate()) > 0.0005).sorted(Comparator.comparingDouble(mp -> -Math.abs(mp.lastFundingRate()))).limit(5).toList();
        LOGGER.info("Top 5 Funding Rates: {}", top5FundingRates);
        return top5FundingRates;
    }

    public AccountInfoDTO getAccountInformation() {
        String response = umFuturesClient.account().accountInformation(new LinkedHashMap<>());
        AccountInfoDTO accountInfo = null;
        try {
            accountInfo = objectMapper.readValue(response, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            LOGGER.error("Invalid JSON", e);
        }

        if (accountInfo != null) {
            filterNonZeroAssestsAndPositions(accountInfo);
            accountInfoRepository.save(accountInfo);
            LOGGER.info("Account Info Updated.");
        }

        return accountInfo;
    }

    private void filterNonZeroAssestsAndPositions(AccountInfoDTO accountInfo) {
        accountInfo.assets().removeIf(asset -> asset.marginBalance() == 0);
        accountInfo.positions().removeIf(position -> position.positionAmt() == 0);
    }
}
