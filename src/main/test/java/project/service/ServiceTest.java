package project.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import project.dao.api.ClientDao;
import project.dao.api.ContractDao;
import project.dao.api.OptionDao;
import project.dao.api.TariffDao;
import project.model.*;
import project.service.impl.ClientServiceImpl;
import project.service.impl.ContractServiceImpl;
import project.service.impl.OptionServiceImpl;
import project.service.impl.TariffServiceImpl;

import java.sql.Date;
import java.util.*;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ServiceTest {

    @Mock
    private ClientDao clientDao;
    @Mock
    private ContractDao contractDao;
    @Mock
    private OptionDao optionDao;
    @Mock
    private TariffDao tariffDao;

    @InjectMocks
    private ClientServiceImpl clientService;
    @InjectMocks
    private ContractServiceImpl contractService;
    @InjectMocks
    private OptionServiceImpl optionService;
    @InjectMocks
    private TariffServiceImpl tariffService;

    @Spy
    private List<ClientEntity> clients = new ArrayList<>();
    @Spy
    private List<ContractEntity> contracts = new ArrayList<>();
    @Spy
    private List<OptionEntity> options = new ArrayList<>();
    @Spy
    private List<TariffEntity> tariffs = new ArrayList<>();

    @InjectMocks
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ClientEntity clientEntity2;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        clients = getClientList();
        contracts = getContractsList();
        options = getOptionList();
        tariffs = getTariffList();
    }

//____ClientService___________________________________________________________________

    @Test
    public void findByEmail() {
        ClientEntity client = clients.get(0);
        when(clientDao.findByEmailOfEmail(anyString())).thenReturn(client);
        Assert.assertEquals(clientService.findByEMail(anyString()), client);
    }

    @Test
    public void updateClient() {
        ClientEntity client = clients.get(0);
        when(clientDao.getClientId(anyInt())).thenReturn(client);
        clientService.updateClient(client);
        verify(clientDao, atLeastOnce()).getClientId(anyInt());
    }

    @Test
    public void removeClient() {
        doNothing().when(clientDao).removeClient(anyInt());
        clientService.removeClient(anyInt());
        verify(clientDao, atLeastOnce()).removeClient(anyInt());
    }

    @Test
    public void getClientId() {
        ClientEntity client = clients.get(0);
        when(clientDao.getClientId(anyInt())).thenReturn(client);
        Assert.assertEquals(clientService.getClientId(client.getIdClient()), client);
    }

    @Test
    public void listClients() {
        when(clientDao.listClients()).thenReturn(clients);
        Assert.assertEquals(clientService.listClients(), clients);
    }

//__ContractService_________________________________________________

    @Test
    public void getContractId() {
        ContractEntity contract = contracts.get(0);
        when(contractDao.getContract(anyInt())).thenReturn(contract);
        Assert.assertEquals(contractService.getContract(contract.getIdContract()), contract);
    }

    @Test
    public void updateContract() {
        ContractEntity contractEntity = contracts.get(0);
        when(contractDao.getContract(anyInt())).thenReturn(contractEntity);
        contractService.update(contractEntity);
        verify(contractDao, atLeastOnce()).getContract(anyInt());
    }

    @Test
    public void listContract() {
        when(contractDao.list()).thenReturn(contracts);
        Assert.assertEquals(contractService.list(), contracts);
    }

    @Test
    public void listContractByClientId() {
        ClientEntity clientEntity = clients.get(0);
        List<ContractEntity> contractList = clientEntity.getContracts();
        when(contractDao.listById(anyInt())).thenReturn(contractList);
        Assert.assertEquals(contractService.listById(clientEntity.getIdClient()), contractList);

    }

    @Test
    public void deleteConnectOptions() {
        doNothing().when(contractDao).deleteConnectOptions(anyInt());
        contractService.deleteConnectOptions(anyInt());
        verify(contractDao, atLeastOnce()).deleteConnectOptions(anyInt());
    }

// ___OptionService_____________________________________________________________________

    @Test
    public void updateOptions() {
        OptionEntity optionEntity = options.get(0);
        when(optionDao.getOption(anyInt())).thenReturn(optionEntity);
        optionService.update(optionEntity);
        verify(optionDao, atLeastOnce()).getOption(anyInt());
    }

    @Test
    public void removeOption() {
        doNothing().when(optionDao).deleteOption(anyInt());
        optionService.deleteOption(anyInt());
        verify(optionDao, atLeastOnce()).deleteOption(anyInt());
    }

    @Test
    public void getOption() {
        OptionEntity optionEntity = options.get(0);
        when(optionDao.getOption(anyInt())).thenReturn(optionEntity);
        Assert.assertEquals(optionService.getOption(optionEntity.getIdOption()), optionEntity);
    }

    @Test
    public void listOptions() {
        when(optionDao.listAllOptions()).thenReturn(options);
        Assert.assertEquals(optionService.listAllOptions(), options);
    }

    @Test
    public void listOptionByTariffId() {
        TariffEntity tariffEntity = tariffs.get(0);
        List<OptionEntity> optionList = tariffEntity.getOptions();
        when(optionDao.listOptions(anyInt())).thenReturn(optionList);
        Assert.assertEquals(optionService.listOptions(tariffEntity.getIdTariff()), optionList);
    }

//______TariffService_______________________________________

    @Test
    public void updateTariffs() {
        TariffEntity tariffEntity = tariffs.get(0);
        when(tariffDao.getTariff(anyInt())).thenReturn(tariffEntity);
        tariffService.update(tariffEntity);
        verify(tariffDao, atLeastOnce()).getTariff(anyInt());
    }

    @Test
    public void removeTariff() {
        doNothing().when(tariffDao).remove(anyInt());
        tariffService.remove(anyInt());
        verify(tariffDao, atLeastOnce()).remove(anyInt());
    }

    @Test
    public void getTariff() {
        TariffEntity tariffEntity = tariffs.get(0);
        when(tariffDao.getTariff(anyInt())).thenReturn(tariffEntity);
        Assert.assertEquals(tariffService.getTariff(tariffEntity.getIdTariff()), tariffEntity);
    }

    @Test
    public void listTariffs() {
        when(tariffDao.listTariffs()).thenReturn(tariffs);
        Assert.assertEquals(tariffService.listTariffs(), tariffs);
    }

    private List<ClientEntity> getClientList() {
        ClientEntity clientEntity1 = new ClientEntity();
        clientEntity1.setIdClient(0);
        clientEntity1.setAdress("qwer");
        clientEntity1.setDateOfBirth(new Date(1 / 10 / 2000));
        clientEntity1.setEmailOfEmail("qq@qq.ru");
        clientEntity1.setSurname("qwert");
        clientEntity1.setName("wert");
        clientEntity1.setPassportData("12345");
        clientEntity1.setExistingClient("Unblocked");
        clientEntity1.setPassword("12345");

        ClientEntity clientEntity2 = new ClientEntity();
        clientEntity2.setIdClient(1);
        clientEntity2.setAdress("asdf");
        clientEntity2.setDateOfBirth(new Date(1 / 10 / 1999));
        clientEntity2.setEmailOfEmail("aa@aa.ru");
        clientEntity2.setSurname("asdfg");
        clientEntity2.setName("sdfg");
        clientEntity2.setPassportData("09876");
        clientEntity2.setExistingClient("Unblocked");
        clientEntity2.setPassword("12345");

        clients.add(clientEntity1);
        clients.add(clientEntity2);
        return clients;
    }

    private List<ContractEntity> getContractsList() {
        ContractEntity contract1 = new ContractEntity();
        contract1.setIdContract(0);
        contract1.setContractNumber("89089089089");
        contract1.setAdminBlock("N");
        contract1.setBlockedContract("Unblocked");
        contract1.setClientEntity(clients.get(0));

        ContractEntity contract2 = new ContractEntity();
        contract2.setIdContract(1);
        contract2.setContractNumber("89089089099");
        contract2.setAdminBlock("N");
        contract2.setBlockedContract("Unblocked");
        contract2.setClientEntity(clients.get(1));

        contracts.add(contract1);
        contracts.add(contract2);
        return contracts;
    }

    private List<OptionEntity> getOptionList() {
        OptionEntity optionEntity1 = new OptionEntity();
        optionEntity1.setNameOption("Option1");
        optionEntity1.setPriceOption(100);
        optionEntity1.setConnectionCostOption(10);
        optionEntity1.setCompatibility("Compatible");
        optionEntity1.setContracts(contracts);
        optionEntity1.setIdOption(0);

        OptionEntity optionEntity2 = new OptionEntity();
        optionEntity2.setNameOption("Option2");
        optionEntity2.setPriceOption(100);
        optionEntity2.setConnectionCostOption(10);
        optionEntity2.setCompatibility("Compatible");
        optionEntity2.setContracts(contracts);
        optionEntity2.setIdOption(1);

        options.add(optionEntity1);
        options.add(optionEntity2);
        return options;
    }

    private List<TariffEntity> getTariffList() {
        TariffEntity tariffEntity1 = new TariffEntity();
        tariffEntity1.setNameTariff("Tariff1");
        tariffEntity1.setPriceTariff(100);
        tariffEntity1.setIdTariff(0);
        tariffEntity1.setContracts(contracts);
        tariffEntity1.setOptions(options);

        TariffEntity tariffEntity2 = new TariffEntity();
        tariffEntity1.setNameTariff("Tariff2");
        tariffEntity1.setPriceTariff(100);
        tariffEntity1.setIdTariff(1);
        tariffEntity1.setContracts(contracts);
        tariffEntity1.setOptions(options);

        tariffs.add(tariffEntity1);
        tariffs.add(tariffEntity2);
        return tariffs;
    }
}
