package com.dev.dao.iml;

import com.dev.dao.CityDao;
import com.dev.model.City;
import com.dev.util.ParseListIdToQueryForOperatorInUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CityDaoImp implements CityDao {
    private static final String TABLE_CITIES = "cities";

    @Autowired
    Connection connection;

    @Override
    public Optional<City> getCityByName(String name) throws SQLException {
        String query = String.format("select * from %s where cityName='%s';", TABLE_CITIES, name);
        return Optional.ofNullable(getCityByQuery(query));
    }

    @Override
    public Optional<City> getCityById(Long id) throws SQLException {
        String query = String.format("select * from %s where idCity = '%d';", TABLE_CITIES, id);
        return Optional.ofNullable(getCityByQuery(query));
    }

    public Optional<City> getCityBesidesTheNamesAlreadyExist(List<Long> listId, char lastLetterInCityName)
            throws SQLException {
        String query = String.format("select * from %s where cityName like '%s%%' " +
                        "and idCity not in (%s) limit 1;", TABLE_CITIES, lastLetterInCityName,
                ParseListIdToQueryForOperatorInUtil.parse(listId));
        return Optional.ofNullable(getCityByQuery(query));
    }

    @Override
    public long getNumbersCities() throws SQLException {
        long numbersCity = 0;
        String query = String.format("select count(*) from %s;", TABLE_CITIES);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                numbersCity = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            throw new SQLException("Can't get cities numbers", ex);
        }
        return numbersCity;
    }

    private City getCityByQuery(String query) throws SQLException {
        City city = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                city = new City(
                        resultSet.getLong("idCity"),
                        resultSet.getLong("idRegion"),
                        resultSet.getLong("idCountry"),
                        resultSet.getString("cityName")
                );
            }
        } catch (SQLException ex) {
            throw new SQLException("Can't get city by name", ex);
        }
        return city;
    }
}
