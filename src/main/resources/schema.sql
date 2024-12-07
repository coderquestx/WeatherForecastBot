CREATE TABLE weather_data (
                              id SERIAL PRIMARY KEY,
                              city VARCHAR(255) NOT NULL,
                              temperature DOUBLE PRECISION NOT NULL,
                              description VARCHAR(255) NOT NULL,
                              timestamp TIMESTAMP NOT NULL
);
