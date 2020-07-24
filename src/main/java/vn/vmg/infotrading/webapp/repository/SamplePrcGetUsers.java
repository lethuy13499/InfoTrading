package vn.vmg.infotrading.webapp.repository;

import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.CallableStatementCreator;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class SamplePrcGetUsers implements CallableStatementCreator {

    public SamplePrcGetUsers() {

    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        CallableStatement callableStatement = connection.prepareCall("{call PKG_USERS.PRC_GET_USERS1(?, ?)}");
        callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
        callableStatement.setString(2, "");

        return callableStatement;
    }
}
