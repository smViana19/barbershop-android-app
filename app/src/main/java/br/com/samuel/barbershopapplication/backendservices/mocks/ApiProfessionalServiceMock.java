package br.com.samuel.barbershopapplication.backendservices.mocks;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import br.com.samuel.barbershopapplication.backendservices.api.ApiProfessionalService;
import br.com.samuel.barbershopapplication.model.ApiProfessionalResponse;
import kotlin.coroutines.Continuation;

public class ApiProfessionalServiceMock implements ApiProfessionalService {

    @Override
    public @Nullable Object getAllProfessionals(@NotNull Continuation<? super @NotNull List<@NotNull ApiProfessionalResponse>> $completion) {
        return null;
    }

    @Override
    public @Nullable Object getProfessionalById(int professionalId, @NotNull Continuation<? super @NotNull ApiProfessionalResponse> $completion) {
        return null;
    }
}
