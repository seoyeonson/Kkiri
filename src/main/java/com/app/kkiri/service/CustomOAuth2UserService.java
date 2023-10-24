package com.app.kkiri.service;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.app.kkiri.domain.data.ProviderUserRequest;
import com.app.kkiri.domain.model.ProviderUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	// private final ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		ClientRegistration clientRegistration = userRequest.getClientRegistration();

		OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

		ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration, oAuth2User);

		// ProviderUser providerUser(ProviderUserRequest providerUserRequest) {
		// 	return providerUserConverter.convert(providerUserRequest);
		// }

		return oAuth2User;
	}
}
