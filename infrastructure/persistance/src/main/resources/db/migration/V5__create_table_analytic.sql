CREATE TABLE public.t_analytic
(
    id             uuid PRIMARY KEY,
    idempotency_id uuid UNIQUE NOT NULL
);