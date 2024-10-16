package hu.otp.assignment.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class BooleanStringConverter implements AttributeConverter<Boolean, String> {
    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return dbData.equals("permanent");
    }

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute.equals(true) ? "permanent" : "temporary";
    }
}
