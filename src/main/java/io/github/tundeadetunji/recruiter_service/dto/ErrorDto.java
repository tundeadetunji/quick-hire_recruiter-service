package io.github.tundeadetunji.recruiter_service.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ErrorDto<T> {
    private String message;
    private T result;

    public static <T> ErrorDto<T> create(String message, T result){
        return new ErrorDto<T>(message, result);
    }
}
