package com.ferhatayar.expection;

import java.awt.CardLayout;

import com.ferhatayar.model.Rentals;

import lombok.Getter;

@Getter
public enum MessageType {
	NO_RECORD_EXIST("1004","kayıt bulunamadı"),
	TOKEN_IS_EXPİRED("1005","tokenin süresi bitmiştir"),
	USERNAME_NOT_FOUND("1006","username bulunamadı"),
	USERNAME_OR_PASSWORD_INVALID("1007","kullanıcı adı veya şifre hatalı"),
	REFRESH_TOKEN_NOT_FOUND("1008","refresh token bulunamadı"),
	REFRESH_TOKEN_IS_EXPIRED("1009","refresh tokenın süresi bitmiştir"),
	USER_NOT_FOUND("1010","kayıtlı kullanıcı bulunamadı"),
	ADDRESS_NOT_FOUND("1011","address bulunamadı"),
	FILE_NOT_LOADING("1012","dosya yüklenirken hata oluştu"),
	FILE_NOT_DELETING("1013","dosya silinirken hata oluştu"),
	CAR_NOT_FOUND("1014","car bulunamadı"),
	IMAGE_NOT_FOUND("1015","image bulunamadı"),
	PAYMENT_NOT_FOUND("1016","payment bulunamadı"),
	PURCHASE_NOT_FOUND("1017","purchase bulunamadı"),
	OWNER_NOT_FOUND("1018","owner bulunamadı"),
	RENTAL_NOT_FOUND("1019","rental bulunamadı"),
	GENERAL_EXPECTION("9999","genel bir hata oluştu");
	
	private String code;
	
	private String message;
	
	 MessageType(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
