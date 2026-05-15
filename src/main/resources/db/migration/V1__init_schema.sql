CREATE TABLE routes (
    id              VARCHAR(50)         PRIMARY KEY,
    long_name       VARCHAR(100)        NOT NULL,
    color           VARCHAR(6),
    type            SMALLINT            NOT NULL,
    direction_0     VARCHAR(100),
    direction_1     VARCHAR(100)
);

CREATE TABLE stops (
    id              VARCHAR(50)         PRIMARY KEY,
    name            VARCHAR(100)        NOT NULL,
    parent_station  VARCHAR(50),
    latitude        DOUBLE PRECISION,
    longitude       DOUBLE PRECISION
);

CREATE TABLE predictions (
    id                      VARCHAR(100)    PRIMARY KEY,
    route_id                VARCHAR(50)     REFERENCES routes(id),
    stop_id                 VARCHAR(50)     REFERENCES stops(id),
    trip_id                 VARCHAR(100),
    vehicle_id              VARCHAR(50),
    arrival_time            TIMESTAMPTZ,
    departure_time          TIMESTAMPTZ,
    arrival_uncertainty     INTEGER,
    direction_id            SMALLINT,
    status                  VARCHAR(200),
    schedule_relationship   VARCHAR(50),
    stop_sequence           INTEGER,
    recorded_at             TIMESTAMPTZ     NOT NULL DEFAULT now()
);

CREATE INDEX idx_predictions_stop_id        ON predictions(stop_id);
CREATE INDEX idx_predictions_route_id       ON predictions(route_id);
CREATE INDEX idx_predictions_recorded_at    ON predictions(recorded_at DESC);