DROP TABLE IF EXISTS fixtures;
DROP TABLE IF EXISTS results;
CREATE TABLE fixtures
(
  id bigserial NOT NULL,
  fixture_json json
);

CREATE TABLE results
(
id bigserial NOT NULL,
result_json json
);