package com.midea.junit.test.web;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	/**
	 * 将json改成fastjson，并设置json的特征
	 * 
	 * @return
	 */
	@Bean
	public FastJsonHttpMessageConverter customJackson2HttpMessageConverter() {

		FastJsonHttpMessageConverter jsonConverter = new FastJsonHttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		jsonConverter.setSupportedMediaTypes(supportedMediaTypes);

		jsonConverter.setFeatures(SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteDateUseDateFormat);

		return jsonConverter;
	}

	/**
	 * 设置编码为UTF-8
	 * @return
	 */
	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		return new StringHttpMessageConverter(Charset.forName("UTF-8"));
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(customJackson2HttpMessageConverter());
		converters.add(responseBodyConverter());
		super.addDefaultHttpMessageConverters(converters);
	}
}
