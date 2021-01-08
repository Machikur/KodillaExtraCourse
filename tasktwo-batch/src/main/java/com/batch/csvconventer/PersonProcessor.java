package com.batch.csvconventer;

import com.batch.domain.PersonWithAge;
import com.batch.domain.PersonWithDate;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
class PersonProcessor implements ItemProcessor<PersonWithDate, PersonWithAge> {
    @Override
    public PersonWithAge process(PersonWithDate item) {
        return new PersonWithAge(
                item.getName(),
                item.getSurName(),
                ChronoUnit.YEARS.between(item.getDateOfBirth(), LocalDate.now())
        );
    }
}
