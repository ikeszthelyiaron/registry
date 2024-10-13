package hu.otp.assignment.util;

import jakarta.persistence.AttributeConverter;

@jakarta.persistence.Converter(autoApply = true)
public class Converter implements AttributeConverter<Boolean, String> {
    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return dbData.equals("permanent") ? true : false;
    }

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute.equals("true") ? "permanent" : "temporary";
    }
}
