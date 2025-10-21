package com.dealer.mcp.lab.auto.common.svc.mcp;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ProductContextSvc {
    ObjectNode buildProductContext(String productId);
}
