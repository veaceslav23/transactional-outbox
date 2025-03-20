CREATE TABLE public.t_order
(
    id          UUID PRIMARY KEY,
    total_price NUMERIC(16, 2) NOT NULL,
    created_at  TIMESTAMP      NOT NULL
);