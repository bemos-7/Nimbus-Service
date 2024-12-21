from pydantic import BaseModel


class FileNameSize(BaseModel):
    name: str
    size_mb: float
