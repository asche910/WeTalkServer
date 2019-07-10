package com.asche.wetalk.mapper;


import com.asche.wetalk.entity.ConfirmationToken;

//@Mapper
public interface ConfirmationTokenMapper {

    void addToken(ConfirmationToken token);

    ConfirmationToken findToken(String token);

}
