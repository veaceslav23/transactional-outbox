CREATE TABLE public.t_outbox (
    id UUID PRIMARY KEY,
    event JSONB,
    created_at TIMESTAMP DEFAULT NOW(),
    status VARCHAR(50) DEFAULT 'PENDING'
);