package com.sw8labs.crypto.fundingfeefarmer.dto;

public record AssetDTO(
        String asset,
        double walletBalance,
        double unrealizedProfit,
        double marginBalance,
        double maintMargin,
        double initialMargin,
        double positionInitialMargin,
        double openOrderInitialMargin,
        double crossWalletBalance,
        double crossUnPnl,
        double availableBalance,
        double maxWithdrawAmount,
        boolean marginAvailable,
        long updateTime
) {
}