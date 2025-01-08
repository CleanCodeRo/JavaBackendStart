package com.utcb.javaBackendStart.shared.models;

public interface BaseModel<M extends BaseModel, E extends BaseEntity> {

    E toEntity();
}
