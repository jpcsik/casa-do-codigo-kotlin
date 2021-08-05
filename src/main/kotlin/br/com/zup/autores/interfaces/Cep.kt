package br.com.zup.autores.interfaces

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint
import kotlin.annotation.AnnotationRetention.*
import kotlin.annotation.AnnotationTarget.*

@MustBeDocumented
@Target(FIELD, CONSTRUCTOR)
@Retention(RUNTIME)
@Constraint(validatedBy = [CepValidador::class])
annotation class Cep(val message: String = "Cep Inválido!")

@Singleton
class CepValidador : ConstraintValidator<Cep, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<Cep>,
        context: ConstraintValidatorContext
    ): Boolean {

        if(value.isNullOrBlank()){
            return false
        }

        return value.matches("[0-9]{5}-?[0-9]{3}".toRegex())
    }

}
