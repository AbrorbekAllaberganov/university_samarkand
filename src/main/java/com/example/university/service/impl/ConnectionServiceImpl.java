package com.example.university.service.impl;

import com.example.university.entity.Connection;
import com.example.university.exceptions.ResourceNotFound;
import com.example.university.payload.ConnectionPayload;
import com.example.university.payload.Result;
import com.example.university.repository.ConnectionRepository;
import com.example.university.repository.FacultyRepository;
import com.example.university.repository.ParentRepository;
import com.example.university.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final ParentRepository parentRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public Result saveConnection(ConnectionPayload connectionPayload) {
        try {
            Connection connection = new Connection();
            connection.setFullName(connectionPayload.getFullName());
            connection.setMessage(connectionPayload.getMessage());
            connection.setPhoneNumber(connectionPayload.getPhoneNumber());
            connection.setView(false);
            connectionRepository.save(connection);

            return Result.success(connection);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result deleteConnection(UUID id) {
        try {
            connectionRepository.deleteById(id);
            return Result.message("successful deleted", true);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result changeIsView(UUID id) {
        try {
            Connection connection=connectionRepository.findById(id).orElseThrow(
                    ()->new ResourceNotFound("connection","id",id)
            );
            connection.setView(!connection.isView());
            connectionRepository.save(connection);
            return Result.success(connection);
        }catch (Exception e){
            return Result.exception(e);
        }
    }


    @Override
    public Page<Connection> getConnectionWithPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return connectionRepository.findAll(pageable);
    }

    @Override
    public Result findConnectionById(UUID connectionId) {
        try {
            return Result.success(connectionRepository.findById(connectionId).orElseThrow(
                    ()->new ResourceNotFound("connection","id",connectionId)
            ));
        }catch (Exception e){
            return Result.exception(e);
        }
    }
}
