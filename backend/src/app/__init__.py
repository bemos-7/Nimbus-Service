import logging
from contextlib import asynccontextmanager

from app.builder import ApplicationBuilder
from fastapi import FastAPI


@asynccontextmanager
async def lifespan(app: FastAPI):
    application = ApplicationBuilder(app=app)
    application.build()

    app.state.application = application

    try:
        yield
    except:
        logging.error("Server has been closed.")
    finally:
        logging.debug("Server has been closed.")


app = FastAPI(
    title="Nimbus service",
    version="0.1.0",
    docs_url="/nimbus/api/docs",
    redoc_url="/nimbus/api/redoc",
    openapi_url="/nimbus/api/openapi.json",
    lifespan=lifespan,
)
