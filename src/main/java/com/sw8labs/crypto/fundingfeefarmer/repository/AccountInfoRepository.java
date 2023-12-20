package com.sw8labs.crypto.fundingfeefarmer.repository;

import com.sw8labs.crypto.fundingfeefarmer.dto.AccountInfoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountInfoRepository extends MongoRepository<AccountInfoDTO, String> {
}
