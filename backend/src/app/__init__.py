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


app = FastAPI(lifespan=lifespan)
