package com.academyproject.registration.controllers;

import com.academyproject.registration.requests.CreateKstRequest;
import com.academyproject.registration.resources.KstResource;
import com.academyproject.registration.resources.ResourceHandler;
import com.academyproject.registration.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kst")
@RequiredArgsConstructor
public class KstController {
    private final RegistrationService registrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@RequestBody CreateKstRequest request) {
        KstResource resource = registrationService.store(request);

        return ResourceHandler.generateResponse(
                "Successfully registered to the course",
                resource, HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        registrationService.delete(id);

        return ResourceHandler.successResponse("Successfully deleted kst", HttpStatus.OK);
    }
}
