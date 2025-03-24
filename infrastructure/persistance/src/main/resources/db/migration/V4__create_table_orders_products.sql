CREATE TABLE public.t_orders_products
(
    product_id UUID NOT NULL,
    order_id   UUID NOT NULL,
    quantity   INT  NOT NULL,

    CONSTRAINT pk_product_id_order_id PRIMARY KEY (product_id, order_id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES public.t_product(id) ON DELETE CASCADE,
    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES public.t_order(id) ON DELETE CASCADE
)