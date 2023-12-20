package com.sw8labs.crypto.fundingfeefarmer.dto;


public record MarkPriceDTO(
        String symbol,
        double markPrice,
        double indexPrice,
        double estimatedSettlePrice,
        double lastFundingRate,
        double interestRate,
        long nextFundingTime,
        long time) {
}
