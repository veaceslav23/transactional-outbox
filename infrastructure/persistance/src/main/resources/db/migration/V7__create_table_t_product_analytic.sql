CREATE TABLE public.t_product_analytic
(
    id             uuid PRIMARY KEY,
    quantity       INTEGER     NOT NULL,
    product_id     uuid        NOT NULL,
    analytic_id    uuid        NOT NULL,
    day            date        NOT NULL,

    constraint fk_product_id1 foreign key (product_id) references public.t_product (id),
    constraint fk_product_id foreign key (analytic_id) references public.t_analytic (id)
)