CREATE TABLE public.t_order_line
(
    id          UUID PRIMARY KEY,
    order_id    UUID           NOT NULL,
    total_price NUMERIC(16, 2) NOT NULL,
    product_id UUID NOT NULL,
    constraint fk_product_id foreign key (product_id) references public.t_product(id)
);