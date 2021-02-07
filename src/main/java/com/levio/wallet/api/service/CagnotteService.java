package com.levio.wallet.api.service;

import com.levio.wallet.api.model.Cagnotte;
import com.levio.wallet.api.model.Users;
import javassist.NotFoundException;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CagnotteService {

    public boolean buyTicket(Users buyer,Cagnotte cagnotte,Float amount) throws Exception;
    public List<Cagnotte> getAll();
    public Cagnotte getById(String id);
    public Cagnotte getByAddress(String  address);
}
