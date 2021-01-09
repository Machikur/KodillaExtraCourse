package com.batch.csvconventer;

import com.batch.domain.PersonWithDate;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
class PersonWithDateMapper extends BeanWrapperFieldSetMapper<PersonWithDate> {

    public PersonWithDateMapper() {
        setTargetType(PersonWithDate.class);
    }

    @Override
    public PersonWithDate mapFieldSet(FieldSet fs) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        PersonWithDate personWithDate = new PersonWithDate();
        personWithDate.setName(fs.readString("name"));
        personWithDate.setSurName(fs.readString("surName"));
        String date = fs.readString("dateOfBirth");
        personWithDate.setDateOfBirth(LocalDate.parse(date, formatter));
        return personWithDate;
    }

}
