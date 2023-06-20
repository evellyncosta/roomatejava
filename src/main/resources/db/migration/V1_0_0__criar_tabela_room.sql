CREATE TABLE room (
                      id UUID NOT NULL,
                      number INTEGER NOT NULL,
                      available BOOLEAN NOT NULL,
                      PRIMARY KEY (id)
);

INSERT INTO room (id, number, available) VALUES
                                             ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'::uuid, 101, TRUE),
                                             ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12'::uuid, 102, FALSE),
                                             ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13'::uuid, 103, TRUE);