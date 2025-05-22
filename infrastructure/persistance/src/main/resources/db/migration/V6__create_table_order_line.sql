CREATE TABLE public.t_order_line
(
    id          UUID PRIMARY KEY,
    order_id    UUID           NOT NULL,
    total_price NUMERIC(16, 2) NOT NULL
);