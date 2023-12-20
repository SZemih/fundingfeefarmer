package com.sw8labs.crypto.fundingfeefarmer.service;

import com.sw8labs.crypto.fundingfeefarmer.dto.AccountInfoDTO;
import com.sw8labs.crypto.fundingfeefarmer.dto.MarkPriceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundingFeeFarmerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FundingFeeFarmerService.class);
    private final UMFuturesService umFuturesService;

    @Autowired
    public FundingFeeFarmerService(UMFuturesService umFuturesService) {
        this.umFuturesService = umFuturesService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeAppDataForUser() {
        AccountInfoDTO accountInformation = umFuturesService.getAccountInformation();
        LOGGER.info("Account Information: {}", accountInformation);

        List<MarkPriceDTO> top5FundingRates = umFuturesService.getTop5MarkPricesByAbsLastFundingRate();
        LOGGER.info("Top 5 Funding Rates: {}", top5FundingRates);
    }
}
