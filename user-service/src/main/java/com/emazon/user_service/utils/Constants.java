package com.emazon.user_service.utils;

public class Constants {

    private Constants() {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static final String UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED = "Esta clase no deberías ser instanciada";

    //Messages for exceptions
    public static final String EXCEPTION_MESSAGE = "Mensaje:";

    //Use cases
    public static final String MISSING_EMAIL_EXCEPTION = "El correo no puede ser nulo o vacío";
    public static final String INVALID_EMAIL_EXCEPTION = "El formato de email es inválido";
    public static final String MISSING_PASSWORD_EXCEPTION = "La contraseña no puede ser nula o vacía";
    public static final String USER_DOES_NOT_EXISTS_EXCEPTION = "El usuario con ese correo no existe";

    public static final String MISSING_DOCUMENT_EXCEPTION = "El documento no puede ser nulo o vacío";
    public static final String INVALID_DOCUMENT_EXCEPTION = "El documento debe ser numérico";
    public static final String MISSING_NAME_EXCEPTION = "El nombre no puede ser nulo o vacío";
    public static final String MISSING_LASTNAME_EXCEPTION = "El apellido no puede ser nulo o vacío";
    public static final String MISSING_CELLPHONE_EXCEPTION = "El celular no puede ser nulo o vacío";
    public static final String INVALID_CELLPHONE_EXCEPTION = "El teléfono debe contener máximo 13 caracteres y puede contener el símbolo +";
    public static final String MISSING_DOB_EXCEPTION = "La fecha de nacimiento no puede ser nula";
    public static final String MISSING_ROLE_EXCEPTION = "El rol no puede ser nulo";

    public static final String EMAIL_ALREADY_EXISTS_EXCEPTION = "Ya existe un usuario con este correo";
    public static final String DOCUMENT_ALREADY_EXISTS_EXCEPTION = "Ya existe un usuario con este documento";
    public static final String CELLPHONE_ALREADY_EXISTS_EXCEPTION = "Ya existe un usuario con este celular";

    public static final int MIN_LEGAL_AGE = 18;
    public static final String MINOR_EXCEPTION = "El usuario debe ser mayor de edad";

    public static final String ROLE_DOES_NOT_EXISTS_EXCEPTION = "El rol no existe";






}
