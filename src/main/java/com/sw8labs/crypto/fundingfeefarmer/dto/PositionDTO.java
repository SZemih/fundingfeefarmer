package com.sw8labs.crypto.fundingfeefarmer.dto;

import com.sw8labs.crypto.fundingfeefarmer.enums.PositionSideEnum;

public record PositionDTO(
        String symbol,
        double initialMargin,
        double maintMargin,
        double unrealizedProfit,
        double positionInitialMargin,
        double openOrderInitialMargin,
        int leverage,
        boolean isolated,
        double entryPrice,
        double breakEvenPrice,
        double notional,
        double maxNotional,
        double bidNotional,
        double askNotional,
        PositionSideEnum positionSide,
        double positionAmt,
        double isolatedWallet,
        long updateTime
) {
}