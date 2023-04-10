package com.example.university.service;

import com.example.university.entity.Connection;
import com.example.university.payload.ConnectionPayload;
import com.example.university.payload.Result;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ConnectionService {
    Result saveConnection(ConnectionPayload connectionPayload);
    Result deleteConnection(UUID id);
    Result changeIsView(UUID id);
    Page<Connection> getConnectionWithPageable(int page,int size);
}
