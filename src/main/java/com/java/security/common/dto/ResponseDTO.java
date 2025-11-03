package com.java.security.common.dto;

/**
 * @author Deven Danidhariya
 *
 * @param statusCode status code of request
 * @param message response message of request
 * @param body requested response
 */
public record ResponseDTO(int statusCode, String message, Object body) {

}