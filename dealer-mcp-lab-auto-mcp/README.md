```shell
curl -X POST http://localhost:9080/api/v1/assistant/query \
     -H "Content-Type: application/json" \
     -d '{"query":"Show me product P123 cross-sell recommendations"}'
     
curl -X POST http://localhost:9080/api/v1/assistant/query \
     -H "Content-Type: application/json" \
     -d '{"query": "Tell me about product P123"}'
     
curl -X POST http://localhost:9080/api/v1/assistant/query \
     -H "Content-Type: application/json" \
     -d '{"query": "Dealer D101 show me cross-sell recommendations for product P123"}'

curl -X POST http://localhost:9080/api/v1/assistant/query \
     -H "Content-Type: application/json" \
     -d '{"query": "What is the price of product P123?"}'


curl -X POST http://localhost:9080/api/v1/assistant/query \
     -H "Content-Type: application/json" \
     -d '{"query": "What is DealerMCP and how does it work?"}'

```

| User Query                        | Detected Intent       | Tool / Flow                    |
| --------------------------------- | --------------------- | ------------------------------ |
| “Tell me about P123”              | PRODUCT_CONTEXT       | `/productContext/resolve`      |
| “Dealer D101 cross-sell for P123” | CROSS_SELL            | `/dealerContext/.../recommend` |
| “Cross-sell P123” (no dealer)     | CROSS_SELL → fallback | `/productContext/resolve`      |
| “Price of P123”                   | PRICING               | `/pricingContext/resolve`      |
| Other                             | UNKNOWN               | GPT-only                       |
