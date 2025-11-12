package com.java.security.utils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.stereotype.Component;

/**
 * @author Deven Danidhariya
 */
@Component
public class PhoneNumUtil {

  private PhoneNumUtil() {
  }

  private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

  public static String formatToE164(String countryCode, String phoneNumber) {
    try {
      // Parse number using country ISO (e.g., "US", "IN", "GB")
      Phonenumber.PhoneNumber numberProto = phoneNumberUtil.parse(phoneNumber,
          countryCode.toUpperCase());

      // Validate number
      if (phoneNumberUtil.isValidNumber(numberProto)) {
        // Format in E.164
        return phoneNumberUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
      } else {
        throw new IllegalArgumentException("Invalid phone number for country: " + countryCode);
      }

    } catch (NumberParseException e) {
      throw new IllegalArgumentException("Error parsing phone number: " + e.getMessage(), e);
    }
  }
}
