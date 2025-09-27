package com.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public record StandardResponse<T>(String code) {

}
