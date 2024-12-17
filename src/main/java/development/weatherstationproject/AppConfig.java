package development.weatherstationproject;


import development.weatherstationproject.entity.WeatherData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Configuration
public class AppConfig {
    @Bean
    public WeatherDataRepository weatherDataRepository() {
        return new WeatherDataRepository() {
            @Override
            public List<WeatherData> findAll(Sort sort) {
                return List.of();
            }

            @Override
            public Page<WeatherData> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends WeatherData> S save(S entity) {
                return null;
            }

            @Override
            public <S extends WeatherData> List<S> saveAll(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public Optional<WeatherData> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public List<WeatherData> findAll() {
                return List.of();
            }

            @Override
            public List<WeatherData> findAllById(Iterable<Long> longs) {
                return List.of();
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(WeatherData entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends WeatherData> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends WeatherData> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends WeatherData> List<S> saveAllAndFlush(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public void deleteAllInBatch(Iterable<WeatherData> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public WeatherData getOne(Long aLong) {
                return null;
            }

            @Override
            public WeatherData getById(Long aLong) {
                return null;
            }

            @Override
            public WeatherData getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends WeatherData> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends WeatherData> List<S> findAll(Example<S> example) {
                return List.of();
            }

            @Override
            public <S extends WeatherData> List<S> findAll(Example<S> example, Sort sort) {
                return List.of();
            }

            @Override
            public <S extends WeatherData> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends WeatherData> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends WeatherData> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends WeatherData, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }

            @Override
            public WeatherData findTopByOrderByTimestampDesc() {
                return null;
            }
            // Legg til nødvendige implementasjoner hvis dette er et egendefinert repository
        };
    }

}
