package com.malcolmbaatjies.user.entities.responses;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * ApiErrorResponse
 * <br><br>
 * This class is used to represent the API error response.
 * <br><br>
 * This class is annotated with Lombok's {@link lombok.Getter} and {@link lombok.experimental.SuperBuilder} to generate the getter methods and builder methods during compile time.
 */
@SuperBuilder
@Getter
public class ApiErrorResponse extends ABaseResponse
{
}
