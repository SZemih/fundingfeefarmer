package com.sw8labs.crypto.fundingfeefarmer.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "accountInfo")
public record AccountInfoDTO(
        int feeTier,
        boolean canTrade,
        boolean canDeposit,
        boolean canWithdraw,
        long updateTime,
        boolean multiAssetsMargin,
        int tradeGroupId,
        double totalInitialMargin,
        double totalMaintMargin,
        double totalWalletBalance,
        double totalUnrealizedProfit,
        double totalMarginBalance,
        double totalPositionInitialMargin,
        double totalOpenOrderInitialMargin,
        double totalCrossWalletBalance,
        double totalCrossUnPnl,
        double availableBalance,
        double maxWithdrawAmount,
        List<AssetDTO> assets,
        List<PositionDTO> positions
) {
}
