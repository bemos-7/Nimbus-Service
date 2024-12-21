import os

import aiofiles
from fastapi import HTTPException


async def save_file(self, user_folder: str, filename: str, file_content: bytes) -> str:
    user_dir = self._get_user_dir(user_folder)
    file_path = os.path.join(user_dir, filename)

    if len(file_content) > self.max_file_size_mb * 1024 * 1024:
        raise HTTPException(status_code=413, detail="Размер файла превышает допустимый лимит")

    async with aiofiles.open(file_path, "wb") as f:
        await f.write(file_content)

    return file_path
