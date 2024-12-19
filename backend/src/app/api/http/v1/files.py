import os
from io import BytesIO

from app.repositories.file import FileRepository
from app.repositories.providers import provide_file_repository_stub
from fastapi import APIRouter, Depends, File, Form, Query, Response, UploadFile
from fastapi.responses import FileResponse

files_router = APIRouter(tags=["Files"], prefix="/files/api/v1/files")


@files_router.post("/save_file")
async def create_one(
    user_folder: str = Form(...),
    file: UploadFile = File(...),
    file_repository: FileRepository = Depends(provide_file_repository_stub),
):
    await file_repository.create_one(user_folder, file)
    return Response(content="", status_code=201)


@files_router.get("/list_files", status_code=200)
async def find_all(
    user_folder: str = Query(...),
    file_repository: FileRepository = Depends(provide_file_repository_stub),
):
    return await file_repository.find_all(user_folder)


@files_router.get("/file", status_code=200)
async def find_all(
    user_folder: str = Query(...),
    filename: str = Query(...),
    file_repository: FileRepository = Depends(provide_file_repository_stub),
):
    file_path, file_size = await file_repository.find_one(user_folder, filename)
    return FileResponse(
        file_path,
        media_type="application/octet-stream",
        headers={
            "Content-Disposition": f"attachment; filename={filename}",
            "Content-Length": str(file_size),
        },
    )
