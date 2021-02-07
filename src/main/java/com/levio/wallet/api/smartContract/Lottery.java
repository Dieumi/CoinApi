package com.levio.wallet.api.smartContract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Lottery extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633178155671bc16d674ec800006001556002556003805460ff60a81b1916600160a81b179055610435806100546000396000f3fe60806040526004361061007b5760003560e01c80638da5cb5b1161004e5780638da5cb5b14610110578063a035b1fe14610125578063f94a28ff1461014c578063fcfff16f146101615761007b565b80632852b71c146100805780633ea8b3bc1461008a5780634664611e146100bb57806350b44712146100e6575b600080fd5b61008861018a565b005b34801561009657600080fd5b5061009f61023b565b604080516001600160a01b039092168252519081900360200190f35b3480156100c757600080fd5b506100d061024f565b6040805160ff9092168252519081900360200190f35b3480156100f257600080fd5b5061009f6004803603602081101561010957600080fd5b5035610258565b34801561011c57600080fd5b5061009f61027f565b34801561013157600080fd5b5061013a61028e565b60408051918252519081900360200190f35b34801561015857600080fd5b5061013a610294565b34801561016d57600080fd5b5061017661029a565b604080519115158252519081900360200190f35b600480546001810182556000919091527f8a35acfbc15ff81a39ae7d344fd709f28e8600b4aa8c65c6b64bfe7fe36bd19b0180546001600160a01b031916331790556002805434829004019055604080516020808252600c908201526b10995d081c9958d95a5d995960a21b8183015290517f16a05e8beedf3aa5e1cb43517ab364e5322f9dad9c1c8c4303ebb61b902ee3529181900360600190a160045460051415610239576102396102aa565b565b60035461010090046001600160a01b031681565b60035460ff1681565b6004818154811061026557fe5b6000918252602090912001546001600160a01b0316905081565b6000546001600160a01b031681565b60015481565b60025481565b600354600160a81b900460ff1681565b6102b26102ee565b6003805460ff191660ff929092169190911790556102ce61031e565b600060028190556003805460ff60a81b19169055610239906004906103c1565b60408051426020808301919091524482840152825180830384018152606090920190925280519101206003165b90565b60025460035460048054909160ff1690811061033657fe5b60009182526020822001546040516001600160a01b039091169183156108fc02918491818181858888f19350505050158015610376573d6000803e3d6000fd5b5060035460048054909160ff1690811061038c57fe5b600091825260209091200154600380546001600160a01b0390921661010002610100600160a81b031990921691909117905550565b50805460008255906000526020600020908101906103df91906103e2565b50565b61031b91905b808211156103fc57600081556001016103e8565b509056fea265627a7a72315820d7321c5205aa3b9c48fb68e532e7db926d2b48a73b204210dd0ed26d3e2ec8fb64736f6c63430005100032";

    public static final String FUNC_ACCEPT = "accept";

    public static final String FUNC_LOTTERYTOTAL = "lotteryTotal";

    public static final String FUNC_OPEN = "open";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PRICE = "price";

    public static final String FUNC_TICKETS = "tickets";

    public static final String FUNC_WINNINGADDRESS = "winningAddress";

    public static final String FUNC_WINNINGNUMBER = "winningNumber";

    public static final Event RECEIVED_EVENT = new Event("received", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected Lottery(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Lottery(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Lottery(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Lottery(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ReceivedEventResponse> getReceivedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RECEIVED_EVENT, transactionReceipt);
        ArrayList<ReceivedEventResponse> responses = new ArrayList<ReceivedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReceivedEventResponse typedResponse = new ReceivedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.msg = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ReceivedEventResponse> receivedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ReceivedEventResponse>() {
            @Override
            public ReceivedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(RECEIVED_EVENT, log);
                ReceivedEventResponse typedResponse = new ReceivedEventResponse();
                typedResponse.log = log;
                typedResponse.msg = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ReceivedEventResponse> receivedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECEIVED_EVENT));
        return receivedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> accept(BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ACCEPT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> lotteryTotal() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LOTTERYTOTAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> open() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OPEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> price() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> tickets(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TICKETS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> winningAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_WINNINGADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> winningNumber() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_WINNINGNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static Lottery load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Lottery(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Lottery load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Lottery(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Lottery load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Lottery(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Lottery load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Lottery(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Lottery> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Lottery.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Lottery> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Lottery.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Lottery> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Lottery.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Lottery> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Lottery.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ReceivedEventResponse extends BaseEventResponse {
        public String msg;
    }
}
