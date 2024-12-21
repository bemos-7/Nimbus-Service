import os

import aiofiles
from fastapi import HTTPException


async def save_file(self, user_folder: str, filename: str, file_content: bytes) -> str:
    user_dir = self._get_user_dir(user_folder)
    try:
        file_path = os.path.join(user_dir, filename)
        async with aiofiles.open(file_path, "wb") as f:
            await f.write(file_content)
        return file_path
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Ошибка сохранения файла: {e}")
