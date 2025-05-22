CREATE TABLE public.t_product_analytic
(
    id         uuid PRIMARY KEY,
    quantity   INTEGER NOT NULL,
    product_id uuid    NOT NULL,
    day        date    NOT NULL,
    idempotency_id uuid UNIQUE NOT NULL,

    constraint fk_product_id foreign key (product_id) references public.t_product(id)
)