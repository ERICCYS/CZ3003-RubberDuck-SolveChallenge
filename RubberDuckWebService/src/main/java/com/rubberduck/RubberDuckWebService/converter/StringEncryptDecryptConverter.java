package com.rubberduck.RubberDuckWebService.converter;

import javax.persistence.Converter;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Converter(autoApply = false)
public class StringEncryptDecryptConverter extends AbstractEncryptDecryptConverter<String> {

    public StringEncryptDecryptConverter(CipherMaker cipherMaker) {
        super(cipherMaker);
    }

    public StringEncryptDecryptConverter() {
        this(new CipherMaker());
    }

    @Override
    boolean isNotNullOrEmpty(String attribute) {
        return isNotEmpty(attribute);
    }

    @Override
    String convertStringToEntityAttribute(String dbData) {
        return dbData;
    }

    @Override
    String convertEntityAttributeToString(String attribute) {
        return attribute;
    }
}
