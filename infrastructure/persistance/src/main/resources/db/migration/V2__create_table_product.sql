CREATE TABLE public.t_product
(
    id         UUID PRIMARY KEY,
    price      NUMERIC(16, 2) NOT NULL,
    created_at TIMESTAMP      NOT NULL DEFAULT NOW(),
    category   VARCHAR(50)    NOT NULL,
    name       VARCHAR(100)   NOT NULL
);