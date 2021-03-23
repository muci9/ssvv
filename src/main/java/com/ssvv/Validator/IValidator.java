package com.ssvv.Validator;
import com.ssvv.Exceptions.ValidatorException;
public interface IValidator<E> {
    void validate(E entity) throws ValidatorException;
}