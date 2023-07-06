package com.ingesoft.carro.service.interfaces;

import com.ingesoft.carro.model.TestModel;

public interface TestService {

    public TestModel getAll();

    public TestModel getTest(Long id);

    public TestModel saveTest(TestModel test);

    public void deleteTest(Long id);
}
